#include <stdio.h>

struct Cursor {
    int position;
    int line;
    int column;
    int current;
};

void advance(struct Cursor *cursor) {
    // not implemented yet
    // we need to connect te cursor 
    // to the source bytes stream
}


int main(void) {

    struct Cursor cursor;

    cursor.position = 0;
    cursor.line= 1;
    cursor.column = 1;
    cursor.current = "n";

    printf("Current: %c\n", cursor.current);
    printf("Position: %d\n", cursor.position);

    advance(&cursor);

    printf("Current: %c\n", cursor.current);
    printf("Position: %d\n", cursor.position);

    return 0;

}whats 