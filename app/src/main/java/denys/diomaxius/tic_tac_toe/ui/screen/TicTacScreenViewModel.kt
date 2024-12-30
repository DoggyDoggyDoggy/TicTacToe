package denys.diomaxius.tic_tac_toe.ui.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import denys.diomaxius.tic_tac_toe.data.BoardState

class TicTacScreenViewModel : ViewModel() {
    private val _boardState = MutableLiveData(BoardState.boardState)
    val boardState: LiveData<List<String>> = _boardState

    private val _playerTurn = MutableLiveData("x")
    val playerTurn: LiveData<String> = _playerTurn

    private val _winner = MutableLiveData<String?>()
    val winner: LiveData<String?> = _winner

    val winningCombinations = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6)
    )


    private fun changePlayerTurn(): String {
        return if (_playerTurn.value == "x") {
            _playerTurn.value = "o"
            "x"
        } else {
            _playerTurn.value = "x"
            "o"
        }
    }

    fun resetBoard() {
        _boardState.value = BoardState.boardState
        _playerTurn.value = "x"
        _winner.value = null
    }

    fun placeState(position: Int) {
        if (_winner.value != null) return
        _boardState.value?.let { currentState ->
            val updatedState = currentState.toMutableList()
            updatedState[position] = changePlayerTurn()
            _boardState.value = updatedState
            if (checkWinner(updatedState)) {
                _winner.value = if (_playerTurn.value == "x") "Winner: O" else "Winner: X"
            } else {
                if (isBoardFull(updatedState)) _winner.value = "Draw"
            }
        }
    }


    private fun checkWinner(state: List<String>): Boolean {
        return winningCombinations.any { combination ->
            val (a, b, c) = combination
            state[a].isNotEmpty() &&
            state[a] == state[b] &&
            state[b] == state[c]
        }
    }

    private fun isBoardFull(state: List<String>): Boolean {
        return state.all { it.isNotEmpty() }
    }

}