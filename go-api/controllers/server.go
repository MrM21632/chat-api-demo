package controllers

import (
	"chat_api/database"
	"chat_api/models"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
)

func FindServers(c *gin.Context) {
	var servers []models.Server
	database.Database.Find(&servers)

	c.JSON(http.StatusOK, gin.H{"data": servers})
}

func FindServer(c *gin.Context) {
	var serverId uuid.UUID
	var err error
	if serverId, err = uuid.Parse(c.Param("id")); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Bad Request - invalid UUID provided in URL."})
		return
	}

	var server models.Server
	if err := database.Database.Where("serverid = ?", serverId).First(&server).Error; err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": "Record not found."})
		return
	}

	c.JSON(http.StatusOK, gin.H{"data": server})
}

func CreateServer(c *gin.Context) {
	var input models.CreateServerInput
	if err := c.ShouldBindJSON(&input); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	newServer := models.Server{
		Name: input.Name,
	}
	database.Database.Create(&newServer)
	c.JSON(http.StatusOK, gin.H{"id": newServer.ID})
}
