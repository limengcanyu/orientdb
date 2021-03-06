package com.orientechnologies.orient.core.storage.impl.local.paginated.wal.po.version.v0.paginatedversionstate;

import com.orientechnologies.common.directmemory.OByteBufferPool;
import com.orientechnologies.common.directmemory.OPointer;
import com.orientechnologies.orient.core.storage.cache.OCacheEntry;
import com.orientechnologies.orient.core.storage.cache.OCacheEntryImpl;
import com.orientechnologies.orient.core.storage.cache.OCachePointer;
import com.orientechnologies.orient.core.storage.impl.local.paginated.wal.po.PageOperationRecord;
import com.orientechnologies.orient.core.storage.index.versionmap.OPaginatedVersionStateV0;
import com.orientechnologies.orient.core.storage.version.OVersionPage;
import java.nio.ByteBuffer;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class PaginatedVersionStateV0SetSizePOTest {
  @Test
  public void testRedo() {
    final int pageSize = OVersionPage.PAGE_SIZE;
    final OByteBufferPool byteBufferPool = new OByteBufferPool(pageSize);
    try {
      final OPointer pointer = byteBufferPool.acquireDirect(false);
      final OCachePointer cachePointer = new OCachePointer(pointer, byteBufferPool, 0, 0);
      final OCacheEntry entry = new OCacheEntryImpl(0, 0, cachePointer, false);

      OPaginatedVersionStateV0 versionState = new OPaginatedVersionStateV0(entry);
      versionState.setSize(12);

      entry.clearPageOperations();

      final OPointer restoredPointer = byteBufferPool.acquireDirect(false);
      final OCachePointer restoredCachePointer =
          new OCachePointer(restoredPointer, byteBufferPool, 0, 0);
      final OCacheEntry restoredCacheEntry = new OCacheEntryImpl(0, 0, restoredCachePointer, false);

      final ByteBuffer originalBuffer = cachePointer.getBufferDuplicate();
      final ByteBuffer restoredBuffer = restoredCachePointer.getBufferDuplicate();

      Assert.assertNotNull(originalBuffer);
      Assert.assertNotNull(restoredBuffer);

      restoredBuffer.put(originalBuffer);

      versionState.setSize(42);

      final List<PageOperationRecord> operations = entry.getPageOperations();
      Assert.assertEquals(1, operations.size());

      Assert.assertTrue(operations.get(0) instanceof PaginatedVersionStateV0SetSizePO);
      final PaginatedVersionStateV0SetSizePO pageOperation =
          (PaginatedVersionStateV0SetSizePO) operations.get(0);

      OPaginatedVersionStateV0 restoredPage = new OPaginatedVersionStateV0(restoredCacheEntry);
      Assert.assertEquals(12, restoredPage.getSize());

      pageOperation.redo(restoredCacheEntry);

      Assert.assertEquals(42, restoredPage.getSize());

      byteBufferPool.release(pointer);
      byteBufferPool.release(restoredPointer);
    } finally {
      byteBufferPool.clear();
    }
  }

  @Test
  public void testUndo() {
    final int pageSize = OVersionPage.PAGE_SIZE;

    final OByteBufferPool byteBufferPool = new OByteBufferPool(pageSize);
    try {
      final OPointer pointer = byteBufferPool.acquireDirect(false);
      final OCachePointer cachePointer = new OCachePointer(pointer, byteBufferPool, 0, 0);
      final OCacheEntry entry = new OCacheEntryImpl(0, 0, cachePointer, false);

      OPaginatedVersionStateV0 versionState = new OPaginatedVersionStateV0(entry);
      versionState.setSize(12);

      entry.clearPageOperations();

      versionState.setSize(42);

      final List<PageOperationRecord> operations = entry.getPageOperations();
      Assert.assertEquals(1, operations.size());

      Assert.assertTrue(operations.get(0) instanceof PaginatedVersionStateV0SetSizePO);

      final PaginatedVersionStateV0SetSizePO pageOperation =
          (PaginatedVersionStateV0SetSizePO) operations.get(0);

      final OPaginatedVersionStateV0 restoredPage = new OPaginatedVersionStateV0(entry);

      Assert.assertEquals(42, restoredPage.getSize());

      pageOperation.undo(entry);

      Assert.assertEquals(12, restoredPage.getSize());

      byteBufferPool.release(pointer);
    } finally {
      byteBufferPool.clear();
    }
  }

  @Test
  public void testSerialization() {
    PaginatedVersionStateV0SetSizePO operation = new PaginatedVersionStateV0SetSizePO(12, 42);

    operation.setFileId(42);
    operation.setPageIndex(24);
    operation.setOperationUnitId(1);

    final int serializedSize = operation.serializedSize();
    final byte[] stream = new byte[serializedSize + 1];
    int pos = operation.toStream(stream, 1);

    Assert.assertEquals(serializedSize + 1, pos);

    PaginatedVersionStateV0SetSizePO restoredOperation = new PaginatedVersionStateV0SetSizePO();
    restoredOperation.fromStream(stream, 1);

    Assert.assertEquals(42, restoredOperation.getFileId());
    Assert.assertEquals(24, restoredOperation.getPageIndex());
    Assert.assertEquals(1, restoredOperation.getOperationUnitId());

    Assert.assertEquals(12, restoredOperation.getOldSize());
    Assert.assertEquals(42, restoredOperation.getNewSize());
  }
}
