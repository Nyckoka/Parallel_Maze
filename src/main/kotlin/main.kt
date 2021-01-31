import pt.isel.canvas.*


const val GRID_WIDTH = 30
const val GRID_HEIGHT = 30
const val BACKGROUND_COLOR = WHITE

const val TILE_SIDE = 20
const val TRUE_WIDTH = GRID_WIDTH * TILE_SIDE
const val TRUE_HEIGHT = GRID_HEIGHT * TILE_SIDE
val GRID_X = 0 until GRID_WIDTH
val GRID_Y = 0 until GRID_WIDTH

const val UP = 'w'
const val LEFT = 'a'
const val DOWN = 's'
const val RIGHT = 'd'

val Directions = listOf(UP, LEFT, DOWN, RIGHT)

fun main() {
    onStart {
        val arena = Canvas(TRUE_WIDTH * 2, TRUE_HEIGHT, BACKGROUND_COLOR)
        var game = Game(Player(Position(0, 0)), getMaze("First"), getEnemies())

        arena.onKeyPressed { ke ->
            if(ke.char in Directions){
                game = game.movePlayer(ke.char)
            }
        }

        arena.onTimeProgress(10){
            game = game.copy(enemies = game.enemies.map { it.move() })
            game.enemies.forEach { game.player.collidesWithEnemy(it) }
            arena.drawGame(game)
        }
    }
    onFinish {

    }
}