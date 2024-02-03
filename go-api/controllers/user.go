package controllers

import (
	"chat_api/database"
	"chat_api/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

func FindUsers(c *gin.Context) {
	var users []models.User
	database.Database.Find(&users)

	c.JSON(http.StatusOK, gin.H{"data": users})
}
