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
	database.Database.AutoMigrate(&models.Server{})
}

func LoadEnv() {
	err := godotenv.Load(".env")
	if err != nil {
		log.Fatal("Error loading .env file", err)
	}
}
