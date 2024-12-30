package denys.diomaxius.tic_tac_toe.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import denys.diomaxius.tic_tac_toe.R
import denys.diomaxius.tic_tac_toe.ui.component.TicTacToeBoard


@Composable
fun TicTacScreen(
    ticTacToeViewModel: TicTacScreenViewModel = viewModel()
) {
    val boardState = ticTacToeViewModel.boardState.observeAsState()
    val playerTurn = ticTacToeViewModel.playerTurn.observeAsState()
    val winner = ticTacToeViewModel.winner.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            if (winner.value == null) {
                playerTurn.value?.let {
                    Text(
                        text = "Player: ${it.replaceFirstChar { char -> char.uppercaseChar() }}",
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Text(
                    text = winner.value!!,
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            TicTacToeBoard(
                modifier = Modifier.weight(1f),
                placeState = { ticTacToeViewModel.placeState(it) },
                boardState = boardState
            )
        }

        Button(
            onClick = { ticTacToeViewModel.resetBoard() },
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(text = "Start new game")
        }
    }

}

