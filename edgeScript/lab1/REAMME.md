Step 3: The Smallest Executable C Program

Our goal is not "Hello World."

Our goal is to understand why each line exists.

We'll build the foundation of the Campus Edge compiler.

First line only
int main()

That's it. Don't think about the braces yet.

Let's dissect this line like an engineer.

main

The operating system doesn't search your file looking for where to start.

Instead, there's a convention:

The program's entry point is a function named main.

Think of a building.

The building has many rooms (functions).

The OS always enters through the main door.

That main door is literally called main.

Later we'll have functions like:

read_file()
scan_identifier()
advance()
peek()

But none of those run first.

Execution always begins at main.

Why are there parentheses ()?

This is our next fundamental concept.

When C sees:

main()

it's not saying "run the function."

It's saying:

"This is a function named main."

The parentheses describe what the function accepts as input.

Examples:

int add(int a, int b)

This function accepts two integers.

Our current program has:

int main()

which means:

"The function currently takes no arguments."

(There's a subtle historical detail here. Modern C usually writes int main(void) to explicitly say "no parameters." We'll learn why later.)

Mental model

Imagine a machine.

        main
     ┌────────┐
Input│        │Output
---->│        │---->
     └────────┘

Right now:

Input: none

Output: an integer (later return 0)

One tiny design question

Since we're building a compiler, not a toy program, think like an engineer.

Eventually we'll run:

./ces_compiler script.ces

That means the compiler needs to receive the filename script.ces.

Looking only at today's understanding, do you think this version is enough?

int main()

Or do you think main will eventually need some way to receive information from the operating system when the program starts?

Don't worry about syntax yet. I only want your reasoning about why a compiler would need input before it starts scanning bytes.

input of the file we need to compile right compiler should be aware of the file before actually scan bytes i am not sure 

Exactly. That's the right reasoning. You don't need to know the syntax yet. You identified the real requirement:

The compiler cannot scan bytes until it knows which file to open.

This is how compiler engineers think: requirements first, syntax later.

Step 3.1: How does the OS give information to main()?

This is where C becomes interesting because we're crossing the boundary between the Operating System and our program.

Remember yesterday's pipeline:

User
  │
  ▼
./ces_compiler script.ces
  │
  ▼
Shell
  │
  ▼
Operating System
  │
  ▼
main()

Notice something.

The operating system already knows two things:

Which program to start (ces_compiler)

What the user typed (script.ces)

So the OS needs a way to hand that information to our program.

Think of it like a receptionist

Imagine you're entering a company.

Security (OS) checks your ID.

Receptionist (main) receives a visitor slip.

The slip contains the information you brought.

The OS doesn't expect our program to "guess" which file to compile.

It hands over a small packet of information when main() starts.

What information should the OS give us?

Before learning C's syntax (argc and argv), let's design it ourselves.

Suppose the user runs:

./ces_compiler script.ces

What information does our compiler actually need?

Think in terms of data, not C.

For example, should the OS give us:

A single string?

A list of words?

The full command exactly as typed?

Something else?

Design Challenge

If you were designing an operating system from scratch, what would you hand to main() so that programs like:

./ces_compiler script.ces
./git commit
./python app.py

could all understand what the user requested?

Answer in plain English. We'll compare it to how Unix actually solved this problem, and you'll see why argc and argv are designed the way they are.

i am not suer 

Perfect. "I'm not sure" is actually the right place to stop here, because this is one of those OS design decisions that's obvious only after you see the problem it solves.

I'm not going to introduce argc and argv yet. We'll derive them.

Step 3.2: You're Designing Unix (1970)

Imagine you're Dennis Ritchie, one of C's creators. You need one system that works for every program ever written.

Users might type:

./ces_compiler script.ces

or

git commit -m "first commit"

or

python app.py

The OS cannot build a different interface for every program.

It needs one universal way to pass information.

Option A: One giant string

The OS could pass:

"./ces_compiler script.ces"
Problem

Now our compiler has to figure out:

Where does the program name end?

Where does the filename begin?

What if there are multiple files?

What if there are quotes?

Example:

git commit -m "first commit"

As one string:

"git commit -m first commit"

How do we know "first commit" belongs together?

Every program would have to write its own parser.

Unix avoided that.

Option B: A list of separate words

Instead, before launching the program, the shell does the splitting.

The user types:

./ces_compiler script.ces

The shell prepares:

Position

	

Word




0

	

./ces_compiler




1

	

script.ces

Then it gives that list to the operating system, which hands it to our program.

For another command:

git commit -m "first commit"

The shell produces:

Position

	

Word




0

	

git




1

	

commit




2

	

-m




3

	

first commit

Notice something clever.

The shell already understands quotes.

Our compiler doesn't have to.

Why this is beautiful

This follows a Unix philosophy we'll see repeatedly.

Do one job well.

Shell: Parse what the user typed.

Operating System: Start the program.

Our Compiler: Compile the script.

Each component has one responsibility.

The Real C Interface

Now that we've earned it, here's the actual function signature:

int main(int argc, char *argv[])

Don't memorize it. We'll decode every symbol tomorrow.

For now, only understand the idea:

The OS gives main() a count of how many words there are.

The OS gives main() the list of those words.

That is exactly the design we arrived at.