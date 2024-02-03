package main

import (
	"chat_api/database"
	"chat_api/models"
	"log"

	"github.com/joho/godotenv"
)

func main() {
	LoadEnv()
	LoadDatabase()
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
