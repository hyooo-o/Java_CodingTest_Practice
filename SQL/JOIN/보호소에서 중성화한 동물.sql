SELECT A.ANIMAL_ID, A.ANIMAL_TYPE, A.NAME
FROM ANIMAL_INS A
JOIN ANIMAL_OUTS B
    ON A.ANIMAL_ID = B.ANIMAL_ID 
        AND A.SEX_UPON_INTAKE != B.SEX_UPON_OUTCOME;