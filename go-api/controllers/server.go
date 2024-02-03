package controllers

import (
	"chat_api/database"
	"chat_api/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

func FindServers(c *gin.Context) {
	var servers []models.Server
	database.Database.Find(&servers)

	c.JSON(http.StatusOK, gin.H{"data": servers})
}
