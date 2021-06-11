package custom;

public interface SpanModel {
   // cell spanning is only run when isCellSpanEnabled() returns true.
   public boolean isCellSpanEnabled();

   // Returns the CellSpan for the given row/column index.
   public CellSpan getCellSpanAt(int rowIndex, int columnIndex);
}
