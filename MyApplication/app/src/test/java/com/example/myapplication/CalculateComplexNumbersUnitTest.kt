package com.example.myapplication

import android.app.Instrumentation
import android.util.AndroidException
import androidx.lifecycle.AndroidViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculateComplexNumbersUnitTest {
    @Test
    fun calc_isCorrect() {
        val CalculateComplexNumbers = CalculateTwoComplexNumbers()
        assertEquals(2.0, CalculateComplexNumbers.mulforim(1.0,1.0, 1.0, 1.0))
    }
}