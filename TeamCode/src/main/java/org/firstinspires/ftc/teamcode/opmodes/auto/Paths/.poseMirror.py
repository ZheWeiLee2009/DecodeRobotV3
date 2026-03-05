"""
mirror_paths.py

This script mirrors PedroPathing Java paths for FTC robots.
It updates Pose X values, heading angles, and ensures the class
and constructor names match the output file name.

Usage (under the same path):
    python .poseMirror.py <input_file> [-o <output_file>]

Parameters:
    input_file       Path to the input Java file to be mirrored.
    -o, --output     Optional path for the output Java file.
                     If not provided, defaults to input_file_mirrored.java.
                     The script will never overwrite the input file.

Behavior:
    - Mirrors all Pose X coordinates: new Pose(x, y) → new Pose(144 - x, y)
    - Mirrors all angles in Math.toRadians(n) → Math.toRadians((180 - n) % 360)
    - Updates the public class name to match the output file name
    - Updates the constructor name to match the output file name
    - Ensures no accidental overwrites; will fail if output file exists
    - Prevent lost timings, stop behaviors, deceleration, etc..

Example:
    python mirror_paths.py untestedRedPaths.java
    python mirror_paths.py untestedRedPaths.java -o autoBluePaths.java
"""

import re
import argparse
import sys
from pathlib import Path

# ---------------- Constants ----------------
FIELD_WIDTH = 144.0

# ---------------- Regex patterns ----------------
POSE_REGEX = re.compile(
    r'new\s+Pose\s*\(\s*([\-0-9]*\.?[0-9]+)\s*,\s*([\-0-9]*\.?[0-9]+)\s*\)'
)

RADIAN_REGEX = re.compile(
    r'Math\.toRadians\s*\(\s*([\-0-9]*\.?[0-9]+)\s*\)'
)

CLASS_REGEX = re.compile(
    r'(public\s+class\s+)(\w+)'  # captures "public class " + classname
)

CONSTRUCTOR_REGEX = re.compile(
    r'(public\s+)(\w+)(\s*\([^)]*\)\s*\{)'  # captures "public <classname>(...) {"
)

# ---------------- Transform functions ----------------
def mirror_pose(match):
    x = float(match.group(1))
    y = match.group(2)

    mirrored_x = FIELD_WIDTH - x
    return f"new Pose({mirrored_x:.3f}, {y})"

def mirror_radians(match):
    degrees = float(match.group(1))

    mirrored = 180 - degrees
    normalized = mirrored % 360

    return f"Math.toRadians({normalized:.3f})"

def update_class_and_constructor(content, new_classname):
    # Update class name
    content = CLASS_REGEX.sub(rf"\1{new_classname}", content, count=1)
    # Update constructor name (all constructors)
    content = CONSTRUCTOR_REGEX.sub(rf"\1{new_classname}\3", content)
    return content

# ---------------- Processing ----------------
def process_content(content, new_classname):
    content = POSE_REGEX.sub(mirror_pose, content)
    content = RADIAN_REGEX.sub(mirror_radians, content)
    content = update_class_and_constructor(content, new_classname)
    return content

# ---------------- CLI ----------------
def main():
    parser = argparse.ArgumentParser(
        description="Mirror PedroPathing Pose X values, heading angles, and update class + constructor names."
    )

    parser.add_argument(
        "input",
        type=Path,
        help="Input Java file"
    )

    parser.add_argument(
        "-o", "--output",
        type=Path,
        help="Optional output file name. Defaults to input_file_mirrored.java"
    )

    args = parser.parse_args()

    if not args.input.exists():
        print("Error: Input file does not exist.", file=sys.stderr)
        sys.exit(1)

    # Determine output file
    if args.output:
        output_file = args.output
    else:
        output_file = args.input.with_name(
            args.input.stem + "_mirrored" + args.input.suffix
        )

    # Prevent accidental overwrite
    if output_file.exists():
        print(f"Error: Output file '{output_file}' already exists. Choose a different name.", file=sys.stderr)
        sys.exit(1)

    # New class/constructor name is the output file stem
    new_classname = output_file.stem

    # Read, process, write
    content = args.input.read_text()
    new_content = process_content(content, new_classname)
    output_file.write_text(new_content)

    print(f"Mirroring complete → {output_file} (class & constructor name updated to {new_classname})")

if __name__ == "__main__":
    main()
