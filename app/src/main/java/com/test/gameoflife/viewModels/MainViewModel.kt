package com.test.gameoflife.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.gameoflife.models.Cell
import com.test.gameoflife.services.GameService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gameService: GameService
) : ViewModel() {

    private val _isOngoing = MutableStateFlow(false)
    val isOngoing: StateFlow<Boolean> = _isOngoing

    private val _cells = MutableStateFlow(ArrayList<CellViewModel>())
    val cells: StateFlow<ArrayList<CellViewModel>> = _cells

    init {
        resetBoard()
    }

    fun resetBoard() {
        viewModelScope.launch {
            _cells.emit(gameService.newBoard())
            _isOngoing.emit(false)
        }
    }

    fun nextGeneration() {
        viewModelScope.launch {
            gameService.updateBoard(cells.value)
            _isOngoing.emit(true)
        }
    }

}