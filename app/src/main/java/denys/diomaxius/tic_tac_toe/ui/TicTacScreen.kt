package denys.diomaxius.tic_tac_toe.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import denys.diomaxius.tic_tac_toe.R


@Composable
fun TicTacScreen() {
    TicTacToeBoard()
}

/*
Box(
                modifier = Modifier
                    .border(BorderStroke(2.dp, Color.Gray))
                    .size(50.dp)
                    .clickable { ticTacToeViewModel.placeState(0)}
            ) {
                if (boardState.value?.get(0) == "x") {
                    Icon(
                        modifier = Modifier.align(alignment = Alignment.Center),
                        painter = painterResource(id = R.drawable.x),
                        contentDescription = ""
                    )
                } else if (boardState.value?.get(0) == "o") {
                    Icon(
                        modifier = Modifier.align(alignment = Alignment.Center),
                        painter = painterResource(id = R.drawable.o),
                        contentDescription = ""
                    )
                }
            }
*/
@Composable
fun TicTacToeBoard(
    ticTacToeViewModel: TicTacScreenViewModel = viewModel()
) {
    val boardState = ticTacToeViewModel.boardState.observeAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..2) {
            Row(

            ) {
                for (x in 0..2) {
                    val position = i * 3 + x
                    Box(
                        modifier = Modifier
                            .border(BorderStroke(2.dp, Color.Gray))
                            .size(100.dp)
                            .clickable {
                                if (boardState.value?.get(position).isNullOrEmpty()
                                ) {
                                    ticTacToeViewModel.placeState(position)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        when (boardState.value?.get(position)) {
                            "x" -> Icon(
                                painter = painterResource(id = R.drawable.x),
                                contentDescription = null
                            )

                            "o" -> Icon(
                                painter = painterResource(id = R.drawable.o),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}