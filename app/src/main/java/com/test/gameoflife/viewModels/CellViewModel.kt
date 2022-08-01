package com.test.gameoflife.viewModels

import android.graphics.Color
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.test.gameoflife.BR
import com.test.gameoflife.models.Cell

class CellViewModel(
    val cell: Cell,
    @get:Bindable var color: Int = Color.WHITE
) : BaseObservable() {

    fun onClick() {
        cell.isAlive = !cell.isAlive
        color = getStateColor()
        notifyPropertyChanged(BR.color)
    }

    fun updateCell(isAlive: Boolean) {
        cell.isAlive = isAlive
        color = getStateColor()
        notifyPropertyChanged(BR.color)
    }

    private fun getStateColor(): Int {
        return if (cell.isAlive) {
            Color.GREEN
        } else {
            Color.WHITE
        }
    }
}