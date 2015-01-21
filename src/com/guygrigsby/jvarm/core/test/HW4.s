* Homewwork 4 part 1 - Guy Grigsby

MAX_LEN	EQU	100	
	
	AREA	HW4, CODE
	ENTRY

main	LDR R1, =StrOne					; Pointer to addy for StrOne
	LDR R2, =StrTwo					; Pointer to addy for StrTwo
	LDR R0, =MixStr					; Pointer to addy for MixStr
	MOV R5, R0					; keep beginning of MixStr
MixLoop
	LDRB R3, [R1], #1				; load in R3 the letter from StrOne
	CMP R3, #0					; if null move other string over
	BEQ MoveRest2
	LDRB R4, [R2], #1				; load in R4 letter from StrTwo
	CMP R4, #0					; if null move other string over
	BEQ MoveRest1
	STRB R3, [R0], #1				; store letter from StrOne
	STRB R4, [R0], #1				; store letter from StrTwo
	B MixLoop
MoveRest1						; StrTwo is shorter so this moves the
	LDRB R3, [R1], #1				; rest of StrOne
	CMP R3, #0
	STRB R3, [R0], #1
	BEQ Finish
	B MoveRest1
MoveRest2						; StrOne is shorter so this moves the
	LDRB R4, [R2], #1				; rest of StrTwo
	CMP R4, #0
	STRB R4, [R0], #1
	BEQ Finish
	B MoveRest2
Finish
	MOV R0, R5
	SWI 2
	SWI 0x11

	AREA	data, DATA

StrOne	DCB	"Hello Metro State!", 0
StrTwo	DCB	"I like assembly programming", 0
MixStr	% MAX_LEN + 1 					; reserve space for mixed string
	END