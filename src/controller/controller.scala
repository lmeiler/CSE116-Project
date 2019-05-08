package controller

import javafx.event._
  import main._
  import view._

  import physics._
  import objects._
  import play.api._
  import javafx.scene.input.KeyEvent
  import javafx.event.EventHandler
import scala.collection.mutable
//each class is a different key press

class PressMovement(player: Player) extends EventHandler[KeyEvent] {

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode.getName match {
          case "A" => player.rightRelease()
//          case "W" => player.jumpReleased()
          case "D" => player.rightRelease()
          case _ =>
      }
      case "KEY_PRESSED" => keyCode.getName match {
        case "A" => player.leftPressed()
        case "W" => player.jump()
        case "D" => player.rightPressed()
        case "V" =>player.shoot()

        case _ =>
      }
      case _ =>

    }
  }
}
