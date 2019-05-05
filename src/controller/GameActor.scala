package controller

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import main.{Database, Player}
import physics.{Boundary, PhysicsVector, World}

class GameActor(userName:String) extends Actor {
  val location = new PhysicsVector(0,0)
  val velocity = new PhysicsVector(0,0)
  val boundaries:List[Boundary] = List()
  val game = new World(boundaries)
  var player = new Player(location, velocity , userName, game)

  override def receive: Receive = {
    case message:AddPlayer =>
      if(Database.playerExists(message.username)){
        val newPlayer = Database.loadPlayer(message.username,player)
        game.players += newPlayer
      }
      else if (!Database.playerExists(message.username)) {
          Database.createPlayer(message.username, new PhysicsVector(message.x, message.y))
          val newPlayer = Database.loadPlayer(message.username,player)
          game.players += newPlayer
      }
    case message:RemovePlayer => Database.removePlayer(message.username)
      game.players.foreach { player =>
        if (player.username == message.username) {
          game.players = game.players - player
        }
      }
    case SendGameState =>
      game.update(System.nanoTime())
      if (game != null) sender() ! GameState(game.gameState())
    case message:KillPlayer => Database.removePlayer(message.username)
      Database.createPlayer(message.username,new PhysicsVector(message.x,message.y))
    case message:movePlayer =>
      game.players.foreach { player =>
        if (player.username == message.username){
          player.location = new PhysicsVector(message.x, message.y)
        }
      }
    case Update => game.update(System.nanoTime())


  }
}
