package controller

  import javafx.event._
  import main._
  import physics._
  import objects._
  import play.api._
  import javafx.scene.input.KeyEvent
  import javafx.event.EventHandler
  import
//each class is a different key press

class PressMovement(player: Player) extends EventHandler[KeyEvent] {
  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode.getName match {
        case "A" => player.leftRelease()
        case "D" => player.rightRelease()
          case _ =>
      }
      case "KEY_PRESSED" => keyCode.getName match {
        case "A" => player.moveLeft()
        case "W" => player.jump()
        case "D" => player.moveRight()
        case _ =>
      }
      case _ =>

    }



  }
}
class shoot(player: Player) extends EventHandler[KeyEvent] {
  override def handle(event: KeyEvent): Unit = {
    player.shoot()
  }

}