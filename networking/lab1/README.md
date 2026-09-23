 A small tool that discovers and reports the machine's network identity. 
 
 Create a command that can answer one question:
"What network interfaces exist on this machine?"

Build Artifact v0.1: Network Interface Collector
Goal: Collect all network interfaces from the OS into memory.
Git target:
feat(network): add network interface collector

Understanding the Data Structure Before Coding

Java returns an Enumeration<NetworkInterface>.

Before touching syntax, understand what an Enumeration actually is.

Imagine the kernel has a shelf of network interfaces.

An Enumeration is like a librarian handing you one book at a time.

You can ask:

"Is there another one?"

Then:

"Give me the next one."

Unlike a List, you cannot jump to item 3 directly.

That's why we'll convert it into a List.