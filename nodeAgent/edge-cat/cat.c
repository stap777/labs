#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

int main(int argc, char *argv[]) {

    if (argc != 2) {
        printf("Usage: ./edge-cat <filename>\n");
        return 1;
    }

    int fd = open(argv[1], O_RDONLY);

    if (fd == -1) {
        printf("edge-cat: %s\n", strerror(errno));
        return 1;
    }

    char buffer[8];
    ssize_t bytes_read;

    while ((bytes_read = read(fd, buffer, sizeof(buffer))) > 0) {

        ssize_t total_written = 0;

        while (total_written < bytes_read) {

            ssize_t bytes_written = write(
                STDOUT_FILENO,
                buffer + total_written,
                bytes_read - total_written
            );
            if (bytes_written == -1) {
                printf("edge-cat: %s\n", strerror(errno));
                close(fd);
                return 1;
            }
            
            total_written += bytes_written;
        }
    }

    if (bytes_read == -1) {
        printf("edge-cat: %s\n", strerror(errno));
        close(fd);
        return 1;
    }
    

    close(fd);

    return 0;
}
