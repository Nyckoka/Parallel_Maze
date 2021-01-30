import pt.isel.canvas.*

data class Game(val player: Player, val maze: Maze)

fun Game.movePlayer(dir: Char) : Game{
    val movedPlayer = player.move(dir)

    if(maze.blocks.none { movedPlayer.position == it.position }){
        return copy(player = movedPlayer)
    }

    return this
}

fun Canvas.drawGame(game: Game){
    erase()

    drawMaze(game.maze)
    drawPlayer(game.player)
}