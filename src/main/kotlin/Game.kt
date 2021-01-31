import pt.isel.canvas.*

data class Game(val player: Player, val maze: Maze, val enemies: List<Enemy>)

fun Game.movePlayer(dir: Char) : Game{
    val movedPlayer = player.move(dir)

    if(!movedPlayer.collidesWithMaze(maze) && movedPlayer.inBounds()){
        return copy(player = movedPlayer)
    }

    return this
}

fun Canvas.drawGame(game: Game){
    erase()

    drawMaze(game.maze)
    game.enemies.forEach { drawEnemy(it.parallel()) }
    drawPlayer(game.player)
}