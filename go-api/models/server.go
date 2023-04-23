package models

import "gorm.io/gorm"

type Tabler interface {
	TableName() string
}

type Server struct {
	gorm.Model
	ID   string `gorm:"column:serverid"`
	Name string `gorm:"column:server_name"`
}

func (Server) TableName() string {
	return "server"
}
