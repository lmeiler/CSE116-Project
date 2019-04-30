package main

import java.sql.{Connection, DriverManager, ResultSet}

import physics.PhysicsVector

object Database {

  val url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username = "root"
  val password = "12345678"
  var connection: Connection = DriverManager.getConnection(url, username, password)

  def createTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS players (username TEXT, locationx DOUBLE, locationy DOUBLE, velocityx DOUBLE, velocityy DOUBLE, health DOUBLE")
  }

  def playerExists(username: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")

    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()

    result.next()
  }

  def createPlayer(username: String, location: PhysicsVector): Unit = {
    val statement = connection.prepareStatement("INSERT INTO players VALUE (?, ?, ?, ?, ?, ?)")

    statement.setString(1, username)
    statement.setDouble(2, location.x)
    statement.setDouble(3, location.y)
    statement.setDouble(4, 0.0)
    statement.setDouble(5, 0.0)
    statement.setInt(6, 10)
  }

  def removePlayer(username: String): Unit = {
    val statement = connection.prepareStatement("DELETE FROM players WHERE username=?")

    statement.setString(1, username)
    statement.execute()
  }
}
