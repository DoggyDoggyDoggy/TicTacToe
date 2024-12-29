package denys.diomaxius.tic_tac_toe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import denys.diomaxius.tic_tac_toe.BoardState

class TicTacScreenViewModel : ViewModel() {
    private val _boardState = MutableLiveData(BoardState.boardState)
    val boardState: LiveData<List<String>> = _boardState

    private val _playerTurn = MutableLiveData("x")
    val playerTurn: LiveData<String> = _playerTurn

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
    }

    fun placeState(position: Int) {
        _boardState.value?.let { currentState ->
            val updatedState = currentState.toMutableList()
            updatedState[position] = changePlayerTurn()
            _boardState.value = updatedState
        }
    }
}