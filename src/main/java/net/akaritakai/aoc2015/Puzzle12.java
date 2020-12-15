package net.akaritakai.aoc2015;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Puzzle12 extends AbstractPuzzle {
    public Puzzle12(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 12;
    }

    @Override
    public String solvePart1() {
        try {
            var mapper = new ObjectMapper();
            var node = mapper.readTree(getPuzzleInput());
            return String.valueOf(traverse(node, false));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String solvePart2() {
        try {
            var mapper = new ObjectMapper();
            var node = mapper.readTree(getPuzzleInput());
            return String.valueOf(traverse(node, true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int traverse(JsonNode node, boolean ignoreRedObjects) {
        if (node.isArray()) {
            var arrayNode = (ArrayNode) node;
            var it = arrayNode.elements();
            var sum = 0;
            while (it.hasNext()) {
                sum += traverse(it.next(), ignoreRedObjects);
            }
            return sum;
        } else if (node.isObject()) {
            if (ignoreRedObjects && isRedObject(node)) {
                return 0;
            }
            var it = node.fields();
            var sum = 0;
            while (it.hasNext()) {
                sum += traverse(it.next().getValue(), ignoreRedObjects);
            }
            return sum;
        } else if (node.isValueNode() && node.isNumber()) {
            return node.asInt();
        } else {
            return 0;
        }
    }

    private boolean isRedObject(JsonNode node) {
        if (node.isObject()) {
            var it = node.fields();
            while (it.hasNext()) {
                var n = it.next().getValue();
                if (n.isValueNode() && n.isTextual() && n.asText().equals("red")) {
                    return true;
                }
            }
        }
        return false;
    }
}
