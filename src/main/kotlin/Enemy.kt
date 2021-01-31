import pt.isel.canvas.*

data class Limit(val x: IntRange, val y: IntRange)
data class Enemy(val position: Position, val velocity: Velocity, val limit: Limit, val parallel: Boolean)

const val ENEMY_COLOR = RED
const val ENEMY_VELOCITY = 4
const val ENEMY_WIDTH = 10
const val ENEMY_HEIGHT = 10

fun Enemy.move() : Enemy{
    val newPosition = position.change(DOWN, velocity.dy).change(RIGHT, velocity.dx)

    if(newPosition.x !in limit.x){
        return copy(position = Position(x = position.x, y = newPosition.y), velocity = velocity.copy(dx = -velocity.dx))
    }
    if(newPosition.y !in limit.y){
        return copy(position = Position(x = newPosition.x, y = position.y), velocity = velocity.copy(dy = -velocity.dy))
    }

    return copy(position = newPosition)
}

fun Enemy.collide(limitX: IntRange, limitY: IntRange) : Enemy{
    val newPosition = position.change(DOWN, velocity.dy)

    if(newPosition.x !in limitX){
        return copy(position = Position(x = position.x, y = newPosition.y), velocity = velocity.copy(dx = -velocity.dx))
    }
    if(newPosition.y !in limitY){
        return copy(position = Position(x = newPosition.x, y = position.y), velocity = velocity.copy(dx = -velocity.dy))
    }

    return copy(position = newPosition)
}

fun Enemy.fixToWindowCenter() = copy(position = position.pixelFixToWindowCenter())

fun Enemy.parallel() = copy(position = position.pixelParallel())

fun Canvas.drawEnemy(enemy: Enemy){
    enemy.fixToWindowCenter().apply {
        if(parallel) parallel().apply {
            drawRect(position.x, position.y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_COLOR)
        }
        else{
            drawRect(position.x, position.y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_COLOR)
        }
    }
}

object EnemyDirection{
    val UP = Velocity(0, -ENEMY_VELOCITY)
    val DOWN = Velocity(0, ENEMY_VELOCITY)
    val RIGHT = Velocity(ENEMY_VELOCITY, 0)
    val LEFT = Velocity(-ENEMY_VELOCITY, 0)
    val DIAGONAL_RIGHT = Velocity(ENEMY_VELOCITY, ENEMY_VELOCITY)
    val DIAGONAL_LEFT = Velocity(-ENEMY_VELOCITY, ENEMY_VELOCITY)
}

val MIDDLE = Position(TRUE_WIDTH / 2 + ENEMY_WIDTH / 2, TRUE_HEIGHT / 2 + ENEMY_HEIGHT / 2)
val GRID_LIMIT = Limit(0..TRUE_WIDTH - ENEMY_WIDTH, 0..TRUE_HEIGHT - ENEMY_HEIGHT)

fun getEnemies() : List<Enemy>{
    val enemies = mutableListOf<Enemy>()

    enemies += Enemy(MIDDLE, EnemyDirection.LEFT, GRID_LIMIT, true)

    enemies += Enemy(MIDDLE, EnemyDirection.UP, GRID_LIMIT, true)

    enemies += Enemy(MIDDLE, EnemyDirection.DOWN, GRID_LIMIT, false)

    return enemies
}