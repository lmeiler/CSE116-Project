package controller

  import javafx.event._
  import main._
  import physics._
  import objects._
  import play.api._
//each class is a different key press

class PressMovement(player: Player, movement:String) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {

    val vector:PhysicsVector = movement match {
      case "a" =>  new PhysicsVector(-1,0)
      case "w" =>  new PhysicsVector(0,-1)
      case "d" =>  new PhysicsVector(1,0)
    }
    player.player_movement(vector)
  }
}
class shoot(player: Player) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    player.shooting()
  }

}