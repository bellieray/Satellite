package com.ebelli.core.domain.usecase.detail

import com.ebelli.core.common.Result
import com.ebelli.core.model.model.SatelliteDetailDto
import com.ebelli.core.data.model.SatelliteDetail
import com.ebelli.core.data.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatelliteDetailUseCaseImpl @Inject constructor(private val satelliteRepository: SatelliteRepository) :
    GetSatelliteDetailUseCase {
    override suspend fun invoke(id: Int): Flow<Result<SatelliteDetailDto?>> = flow {
        val satelliteDetail = satelliteRepository.getSatelliteDetail(id)
        val satellitePositions = satelliteRepository.getSatellitePosition(id)
        val satellites = satelliteRepository.getSatellites()

        combine(satelliteDetail, satellitePositions, satellites) { detail, positions, satellite ->
            when {
                (detail is Result.Success && positions is Result.Success && satellite is Result.Success) -> {
                    (detail.data as? SatelliteDetail)?.let { satelliteDetail ->
                        Result.Success(
                            SatelliteDetailDto(
                                satelliteDetail,
                                satellite.data?.find { it.id == id }?.name,
                                positions.data
                            )
                        )
                    } ?: Result.Success(detail.data as? SatelliteDetailDto)
                }

                (detail is Result.Error || positions is Result.Error || satellite is Result.Error) -> Result.Error(
                    detail?.error ?: positions.error ?: satellite.error
                )

                (detail is Result.Loading) -> Result.Loading()
                else -> Result.Loading()
            }
        }.collect {
            emit(it)
        }
    }
}