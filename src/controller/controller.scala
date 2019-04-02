package controller

  import javafx.event._
  import main._
  import physics._
  import objects._
  import play.api._
  import javafx.scene.input.KeyEvent
  import javafx.event.EventHandler
//each class is a different key press

class PressMovement(player: Player) extends EventHandler[KeyEvent] {
  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode match {
          case "A" => player.player_movement(new PhysicsVector(-1, 0))
          case "W" => player.player_movement(new PhysicsVector(0, -1))
          case "D" => player.player_movement(new PhysicsVector(1, 0))
          case _ =>
      }
      case "KEY_PRESSED" => movement match {
        case "A" => player.player_movement(new PhysicsVector(-1, 0))
        case "W" => player.player_movement(new PhysicsVector(0, -1))
        case "D" => player.player_movement(new PhysicsVector(1, 0))
        case _ =>
      }
      case _ =>

    }



  }
}
class shoot(player: Player) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    player.shooting()
  }

}