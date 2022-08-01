package com.test.gameoflife.models

data class Cell(
    var isAlive: Boolean = false,
    val position: Position
)
