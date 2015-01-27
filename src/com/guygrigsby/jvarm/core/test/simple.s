* this is a test
        AREA     ARMex, CODE, READONLY
                                ; Name this block of code ARMex
        ENTRY                   ; Mark first instruction to execute
		ADD      r0, r0, r1     ; r0 = r0 + r1
        END                     ; Mark end of file