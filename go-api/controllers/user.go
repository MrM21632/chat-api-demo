package controllers

import (
	"chat_api/database"
	"chat_api/models"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
)

func FindUsers(c *gin.Context) {
	var users []models.User
	database.Database.Find(&users)

	c.JSON(http.StatusOK, gin.H{"data": users})
}

func FindUser(c *gin.Context) {
	var userId uuid.UUID
	var err error
	if userId, err = uuid.Parse(c.Param("id")); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Bad Request - invalid UUID provided in URL."})
		return
	}

	var user models.User
	if err := database.Database.Where("userid = ?", userId).First(&user).Error; err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": "Record not found."})
		return
	}

	c.JSON(http.StatusOK, gin.H{"data": user})
}

func CreateUser(c *gin.Context) {
	var input models.CreateUserInput
	if err := c.ShouldBindJSON(&input); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	newUser := models.User{
		Username: input.Username,
		Email:    input.Email,
		Password: input.Password,
		PassSalt: input.PassSalt,
	}
	database.Database.Create(&newUser)
	c.JSON(http.StatusOK, gin.H{"id": newUser.ID})
}
