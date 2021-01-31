

data class Position(val x: Int, val y : Int)
data class Velocity(val dx: Int, val dy : Int)

/**
 * Change a position given a direction of [Directions] and a velocity.
 */
fun Position.change(dir: Char, velocity: Int) =
    when(dir){
        UP -> copy(y = y - velocity)
        LEFT -> copy(x = x - velocity)
        DOWN -> copy(y = y + velocity)
        RIGHT -> copy(x = x + velocity)
        else -> {
            println("Invalid direction")
            copy(x = x + velocity)
        }
    }

operator fun Position.plus(velocity: Velocity) = Position(x + velocity.dx, y + velocity.dy)

/**
 * Position in pixels, where [TILE_SIDE] is multiplied to each tile coordinate
 * in order to produce the actual position in pixels.
 */
fun Position.inPixels() = Position(x = x * TILE_SIDE, y = y * TILE_SIDE)

const val WINDOW_MAZE_WIDTH_DIFFERENCE = WINDOW_GRID_WIDTH - FULL_GRID_WIDTH
const val WINDOW_MAZE_HEIGHT_DIFFERENCE = WINDOW_GRID_HEIGHT - GRID_HEIGHT

fun Position.fixToWindowCenter() : Position{
    return copy(x = x + WINDOW_MAZE_WIDTH_DIFFERENCE / 2, y = y + WINDOW_MAZE_HEIGHT_DIFFERENCE / 2)
}

fun Position.pixelFixToWindowCenter() = copy(
    x = x + (WINDOW_GRID_WIDTH / 2 - FULL_GRID_WIDTH / 2) * TILE_SIDE,
    y = y + (WINDOW_GRID_HEIGHT / 2 - GRID_HEIGHT / 2) * TILE_SIDE)

/**
 * Parallel position, where [GRID_WIDTH] will be added to the tile x coordinate,
 * in order to be drawn in the parallel maze.
 */
fun Position.parallel() = copy(x = x + GRID_WIDTH)

/**
 * Pixel parallel position, where [GRID_WIDTH] * [TILE_SIDE] will be added to the pixel x coordinate,
 * in order to be drawn in the parallel maze.
 */
fun Position.pixelParallel() = copy(x = x + GRID_WIDTH * TILE_SIDE)

operator fun Velocity.times(value: Int) = Velocity(dx * value, dy * value)