package controller

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import main.{Database, Player}
import physics.{Boundary, PhysicsVector, World}

class GameActor() extends Actor {
  val location = new PhysicsVector(0,0)
  val velocity = new PhysicsVector(0,0)
  val boundaries:List[Boundary] = List()
  val game = new World(boundaries)
  //var player = new Player(location, velocity , userName, game)

  override def receive: Receive = {
    case message:AddPlayer => game.addPlayer(message.username)
//      if(Database.playerExists(message.username)){
//        val newPlayer = Database.loadPlayer(message.username,game)
//        game.players += newPlayer
//      }
//      else if (!Database.playerExists(message.username)) {
//          Database.createPlayer(message.username, new PhysicsVector(message.x, message.y))

//          game.players += newPlayer
//      }
    case message:RemovePlayer =>
      //Database.removePlayer(message.username)
      game.players.foreach { player =>
        if (player.username == message.username) {
          game.players = game.players - player
        }
      }
    case Update =>
      game.update()

    case SendGameState => sender() ! GameState(game.gameState())

    case message:KillPlayer => Database.removePlayer(message.username)
      Database.createPlayer(message.username,new PhysicsVector(message.x,message.y))

    case message:movePlayer =>
      game.players.foreach { player =>
        if (player.username == message.username){
          if (message.x == -1){
            player.moveLeft()
          }
          if (message.x == 1){
            player.moveRight()
          }
          if (message.y == -1){
            player.jump()
          }
//          if (message.y == -1){
//
//          }

        }
      }
    case message:Shoot => game.players.foreach(player => if(player.username==message.username) player.shoot())



  }
}
