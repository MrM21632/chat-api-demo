package main

import (
	"chat_api/controllers"
	"chat_api/database"
	"chat_api/models"
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
)

func main() {
	LoadEnv()
	LoadDatabase()

	r := gin.Default()
	r.SetTrustedProxies(nil)

	r.GET("/", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{"data": "Hello, World!"})
	})

	// User API endpoints
	r.GET("/users", controllers.FindUsers)
	r.GET("/users/:id", controllers.FindUser)
	r.POST("/users", controllers.CreateUser)

	// Server API endpoints
	r.GET("/servers", controllers.FindServers)
	r.GET("/servers/:id", controllers.FindServer)
	r.POST("/servers", controllers.CreateServer)

	r.Run("127.0.0.1:4242")
}

func LoadDatabase() {
	database.Connect()
	database.Database.AutoMigrate(&models.User{})
	database.Database.AutoMigrate(&models.Server{})
	database.Database.AutoMigrate(&models.Channel{})
	database.Database.AutoMigrate(&models.Message{})
	database.Database.AutoMigrate(&models.BlockList{})
	database.Database.AutoMigrate(&models.BanList{})
	database.Database.AutoMigrate(&models.ModeratorList{})
}

func LoadEnv() {
	err := godotenv.Load(".env")
	if err != nil {
		log.Fatal("Error loading .env file", err)
	}
}
