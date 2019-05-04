package controller

case object SendGameState
case class GameState(gameState: String)

case class AddPlayer(username:String, x:Double, y: Double)
case class RemovePlayer(username:String)
case class KillPlayer(username:String, x:Double, y:Double )
case class TakeDamage(username:String)
case class movePlayer(username:String, x: Double, y:Double)

case object Update

case object Shoot