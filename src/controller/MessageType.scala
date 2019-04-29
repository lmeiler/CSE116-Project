package controller

case class AddPlayer(username:String)
case class RemovePlayer(username:String)
case class KillPlayer(username:String)
case class TakeDamage(username:String)
case class movePlayer(username:String)

case object Update

case object Shoot