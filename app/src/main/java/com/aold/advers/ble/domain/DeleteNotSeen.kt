package com.aold.advers.ble.domain

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aold.advers.ble.interfaces.IBleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import timber.log.Timber

class DeleteNotSeenWorker(
    appContext: Context, workerParams: WorkerParameters,
    private val bleRepository: IBleRepository,
    private val dispatcher: CoroutineDispatcher
) :
    CoroutineWorker(appContext, workerParams), KoinComponent {

    override suspend fun doWork(): Result = withContext(dispatcher) {
        return@withContext try {
            for (i in 0..14) {
                delay(60000)
                bleRepository.deleteNotSeen()
                Timber.d("ran not seen $i....")
            }
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }
    }

    private val array: IntArray = intArrayOf(0xFFFF0000.toInt())

    fun valsToPacket(
        isSetter: Boolean,
        typeParam: IntArray,
        numParam: IntArray,
        param: LongArray
    ) {
        val packet = ByteArray(20)
        packet[0] = 0x01

        for (i in 0..2) {
            if (isSetter) {
                packet[1] = 0x80.toByte()
            } else {
                packet[1] = 0x00.toByte()
            }
            packet[1 + i * 3] = (packet[1 + i * 3].toInt() + typeParam[i]).toByte()

            //package2
            packet[2 + i * 3] = numParam[i].toByte()
            //package3
            packet[3 + i * 3] = param[i].toByte()
        }
        packet[19] = 0 //todo CRC
    }
}