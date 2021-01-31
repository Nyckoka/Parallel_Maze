import pt.isel.canvas.*


const val WINDOW_GRID_WIDTH = 70
const val WINDOW_GRID_HEIGHT = 30
const val BACKGROUND_COLOR = BLACK

const val GRID_WIDTH = 30
const val GRID_HEIGHT = 10
const val FULL_GRID_WIDTH = GRID_WIDTH * 2

const val TILE_SIDE = 20
const val TRUE_WIDTH = GRID_WIDTH * TILE_SIDE
const val TRUE_HEIGHT = GRID_HEIGHT * TILE_SIDE
const val FULL_TRUE_WIDTH = TRUE_WIDTH * 2

val GRID_X = 0 until GRID_WIDTH
val GRID_Y = 0 until GRID_HEIGHT

const val WINDOW_TRUE_WIDTH = WINDOW_GRID_WIDTH * TILE_SIDE
const val WINDOW_TRUE_HEIGHT = WINDOW_GRID_HEIGHT * TILE_SIDE


const val UP = 'w'
const val LEFT = 'a'
const val DOWN = 's'
const val RIGHT = 'd'

val Directions = listOf(UP, LEFT, DOWN, RIGHT)

fun getStartingGame() = Game(Player(Position(0, 0)), getMaze("First"), getEnemies())

fun main() {
    onStart {
        val arena = Canvas(WINDOW_TRUE_WIDTH, WINDOW_TRUE_HEIGHT, BACKGROUND_COLOR)
        var game = getStartingGame()

        arena.onKeyPressed { ke ->
            if(ke.char in Directions){
                game = game.movePlayer(ke.char)
            }
        }

        arena.onTimeProgress(10){
            game = game.nextFrame()
            arena.drawGame(game)
        }
    }
    onFinish {

    }
}