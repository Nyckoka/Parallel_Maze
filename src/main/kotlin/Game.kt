import pt.isel.canvas.*

data class Game(val player: Player, val maze: Maze, val enemies: List<Enemy>)

fun Game.movePlayer(dir: Char) : Game{
    val movedPlayer = player.move(dir)

    if(movedPlayer.collidesWithFinish(maze.finishBlock)){
        println("Finished!")
        return getStartingGame()
    }

    if(!movedPlayer.collidesWithMaze(maze) && movedPlayer.inBounds()){
        return copy(player = movedPlayer)
    }

    return this
}

fun Game.nextFrame() : Game{
    var newGame = this

    newGame = newGame.copy(enemies = newGame.enemies.map { it.move() })

    if(newGame.enemies.any { newGame.player.collidesWithEnemy(it) }){
        newGame = getStartingGame()
    }

    return newGame
}

fun Canvas.drawGame(game: Game){
    erase()

    //Draw full maze background
    drawRect(Position(0, 0).pixelFixToWindowCenter().x, Position(0, 0).pixelFixToWindowCenter().y, FULL_TRUE_WIDTH, TRUE_HEIGHT, WHITE)

    drawRectTile(Position(0, 0).parallel(), BLUE) //Block marking starting position
    drawMaze(game.maze) //Draw maze blocks
    game.enemies.forEach { drawEnemy(it) } //Draw enemies
    drawPlayer(game.player) //Draw player
}