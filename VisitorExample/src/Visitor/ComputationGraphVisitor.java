package Visitor;

import ComputationGraph.*;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class ComputationGraphVisitor implements Visitor{

    @Override
    public void visitBinaryOperationNode(BinaryOperationNode node) {
        switch (node.operationType) {
            case ADD -> {
                addOperation(node);
            }
            case MULTIPLY_MATRIX -> {
                multiplyMatricesOperation(node);
            }
            case MULTIPLY_SCALAR -> {
                multiplyScalarMatrixOperation(node);
            }
            case SUBTRACT -> {
                subtractOperation(node);
            }
            case INVERSE -> {
                System.out.println("This has no sense!");
            }
        }
    }

    @Override
    public void visitUnaryOperationNode(UnaryOperationNode node) {
        if (node.operationType != OperationType.INVERSE) System.out.println("Something went wrong!");
        node.matrixValue = MatrixUtils.inverse(node.inputNode.matrixValue);
        System.out.println("Calculated inverse of a matrix:");
        printMatrix(node.inputNode.matrixValue);
        System.out.println("Result: ");
        printMatrix(node.matrixValue);
    }

    @Override
    public void visitScalarNode(ScalarNode node) {
        System.out.print("Visiting scalar node. Value:");
        System.out.println(node.scalarValue);
        System.out.println();
    }

    @Override
    public void visitMatrixNode(MatrixNode node) {
        System.out.println("Visiting matrix node: ");
        printMatrix(node.matrixValue);
        System.out.println();
    }

    private void printMatrix(RealMatrix matrix) {
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            System.out.print("[ ");
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                System.out.print(matrix.getEntry(i, j));
                System.out.print(" ");
            }
            System.out.println("]");
        }
    }

    private void addOperation(BinaryOperationNode node) {
        RealMatrix matrixA = node.inputNodeA.matrixValue.copy();
        RealMatrix matrixB = node.inputNodeB.matrixValue.copy();
        node.matrixValue = matrixA.add(matrixB);

        System.out.println("Added two matrices:");
        printMatrix(matrixA);
        System.out.println();
        printMatrix(matrixB);
        System.out.println("Result: ");
        printMatrix(node.matrixValue);
    }

    private void subtractOperation(BinaryOperationNode node) {
        RealMatrix matrixA = node.inputNodeA.matrixValue;
        RealMatrix matrixB = node.inputNodeB.matrixValue;
        node.matrixValue = matrixA.subtract(matrixB);

        System.out.println("Subtracted two matrices:");
        printMatrix(matrixA);
        System.out.println();
        printMatrix(matrixB);
        System.out.println("Result: ");
        printMatrix((RealMatrix) node.matrixValue);
    }

    private void multiplyMatricesOperation(BinaryOperationNode node) {
        RealMatrix matrixA = node.inputNodeA.matrixValue;
        RealMatrix matrixB = node.inputNodeB.matrixValue;
        node.matrixValue = matrixA.multiply(matrixB);

        System.out.println("Multiplied two matrices:");
        printMatrix(matrixA);
        System.out.println();
        printMatrix(matrixB);
        System.out.println("Result: ");
        printMatrix(node.matrixValue);
    }

    private void  multiplyScalarMatrixOperation(BinaryOperationNode node) {
        RealMatrix matrix;
        Double scalar;
        if (node.inputNodeA.matrixValue != null) {
            matrix = node.inputNodeA.matrixValue;
            scalar = node.inputNodeB.scalarValue;
        } else {
            matrix = node.inputNodeB.matrixValue;
            scalar = node.inputNodeA.scalarValue;
        }
        node.matrixValue = matrix.scalarMultiply(scalar);

        System.out.println("Multiplied matrix by scalar:");
        printMatrix(matrix);
        System.out.println();
        System.out.println(scalar);
        System.out.println("Result: ");
        printMatrix(node.matrixValue);
    }


}
