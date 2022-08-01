package com.test.gameoflife.services

import com.test.gameoflife.models.Cell
import com.test.gameoflife.models.Position
import com.test.gameoflife.viewModels.CellViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameService @Inject constructor() {

    private val row = 10
    private val column = 10

    private val neighbors = arrayOf(
        Position(-1, -1),
        Position(-1, 0),
        Position(-1, 1),
        Position(0, -1),
        Position(0, 1),
        Position(+1, -1),
        Position(+1, 0),
        Position(+1, +1)
    )

    fun newBoard(): ArrayList<CellViewModel> {
        val list = ArrayList<CellViewModel>()

        for (x in 0 until row) {
            for(y in 0 until column) {
                val cell = Cell(
                    position = Position(pointX = x, pointY = y)
                )
                list.add(CellViewModel(cell))
            }
        }

        return  list
    }

    fun updateBoard(cellViewModels: ArrayList<CellViewModel>) {
        val tempList = cellViewModels.copy() as ArrayList<Cell>
        for(index in 0 until tempList.size) {
            val currentCell = tempList[index]
            val aliveCount = checkNeighbors(currentCell, tempList)
            if(currentCell.isAlive) {
                if(aliveCount > 3 || aliveCount < 2) {
                    cellViewModels[index].updateCell(false)
                }
            } else {
                if(aliveCount == 3) {
                    cellViewModels[index].updateCell(true)
                }
            }
        }
    }

    private fun checkNeighbors(currentCell: Cell, cells: ArrayList<Cell>): Int {
        var aliveCount = 0
        for(position in neighbors) {
            val pointX = currentCell.position.pointX + position.pointX
            val pointY = currentCell.position.pointY + position.pointY
            val neighbor = getCell(pointX, pointY, cells)
            if(neighbor != null && neighbor.isAlive) {
                aliveCount++
            }
        }
        return  aliveCount
    }

    private fun getCell(x: Int, y: Int, cells: ArrayList<Cell>): Cell? {
        if(x < 0 || x > (row-1) || y < 0 || y > (column-1)) {
            // no neighbor
            return null
        } else {
            for(cell in cells) {
                val position = cell.position
                if(position.pointX == x && position.pointY == y) {
                    return  cell
                }
            }
        }

        return null
    }

    private fun List<CellViewModel>.copy() = map{ it.cell.copy() }
}