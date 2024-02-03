package controllers

import (
	"crypto/rand"
	"crypto/sha256"
	"encoding/hex"
	"fmt"

	"golang.org/x/crypto/pbkdf2"
)

func GenerateSalt() []byte {
	salt := make([]byte, 16)
	if _, err := rand.Read(salt); err != nil {
		fmt.Println("Error occurred: ", err)
		return []byte{}
	}
	return salt
}

// Hashes the given password using PBKDF2, as defined in PKCS #5 v2.0. Returns the resulting hash and the generated salt
// used to create the hash.
//
// For demonstration purposes, this is sufficient. Bear in mind that, for real-world, production-quality systems, you
// should prefer either a higher number of iterations (i.e., in the hundreds of thousands at the very least), or another
// reputable algorithm like Bcrypt.
//
// Furthermore, this is purely meant for demonstrating how such a flow can be achieved in Golang. It's never a good idea
// to have the server handle password encryption; this should always be a responsibility of the client.
func GeneratePassHash(password string) (string, string) {
	passsalt := GenerateSalt()
	passhash := pbkdf2.Key([]byte(password), passsalt, 65536, 256, sha256.New)
	return hex.EncodeToString(passhash), hex.EncodeToString(passsalt)
	// use hex.DecodeString to go from string back to []byte
}
