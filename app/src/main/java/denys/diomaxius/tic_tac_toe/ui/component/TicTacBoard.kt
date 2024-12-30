package denys.diomaxius.tic_tac_toe.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import denys.diomaxius.tic_tac_toe.R

@Composable
fun TicTacToeBoard(
    placeState: (Int) -> Unit,
    boardState: State<List<String>?>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                for (x in 0..2) {
                    val position = i * 3 + x
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .border(BorderStroke(2.dp, Color.Gray))
                            .clickable {
                                if (boardState.value
                                        ?.get(position)
                                        .isNullOrEmpty()
                                ) {
                                    placeState(position)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        when (boardState.value?.get(position)) {
                            "x" -> Icon(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.x),
                                contentDescription = null
                            )

                            "o" -> Icon(
                                modifier = Modifier.fillMaxSize(),
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