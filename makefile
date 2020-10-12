make:
	@echo Encode: make run ARGS=\"encode ciphertext keytext\"
	@echo Decode: make run ARGS=\"decode plaintext keytext\"

run: PlayfairCipher.java
	java PlayfairCipher $(ARGS)

compile: PlayfairCipher.java
	javac PlayfairCipher.java
