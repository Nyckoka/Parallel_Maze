import pt.isel.canvas.*

data class Enemy(val position: Position, val velocity: Velocity)

const val ENEMY_COLOR = RED
const val ENEMY_VELOCITY = 4
const val ENEMY_WIDTH = 10
const val ENEMY_HEIGHT = 10

fun Enemy.move() : Enemy{
    val newPosition = position.change(DOWN, velocity.dy)

    if(newPosition.x < 0){
        return copy(position = Position(x = 0, y = newPosition.y), velocity = velocity.copy(dx = -velocity.dx))
    }
    if(newPosition.x + ENEMY_WIDTH > TRUE_WIDTH){
        return copy(position = Position(x = TRUE_WIDTH, y = newPosition.y), velocity = velocity.copy(dx = -velocity.dx))
    }

    if(newPosition.y < 0){
        return copy(position = Position(x = newPosition.x, y = 0), velocity = velocity.copy(dy = -velocity.dy))
    }
    if(newPosition.y + ENEMY_HEIGHT > TRUE_HEIGHT){
        return copy(position = Position(x = newPosition.x,y = TRUE_HEIGHT - ENEMY_HEIGHT), velocity = velocity.copy(dy = -velocity.dy))
    }

    return copy(position = newPosition)
}

fun Enemy.parallel() = copy(position = position.trueParallel())

fun Canvas.drawEnemy(enemy: Enemy){
    enemy.apply { drawRect(position.x, position.y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_COLOR) }
}

fun getEnemies() : List<Enemy>{
    val enemies = mutableListOf<Enemy>()

    enemies.add(Enemy(Position(TRUE_WIDTH / 2 + ENEMY_WIDTH / 2, TRUE_HEIGHT / 2), Velocity(0, ENEMY_VELOCITY)))

    return enemies
}