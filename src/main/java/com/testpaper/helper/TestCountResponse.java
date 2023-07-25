package com.testpaper.helper;

/**
 * The TestCountResponse class represents a response object containing the count of active tests and total tests.
 * It is used to provide statistical information about the tests.
 * 
 * @see com.testpaper.controller.AdminController#getQuizCount()
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
public class TestCountResponse {
	/**
	* The count of active tests.
	*/
    private long activeCount;
    
    /**
     * The count of total tests.
     */
    private long totalCount;

    /**
     * Constructs a new TestCountResponse object with the specified active count and total count.
     *
     * @param activeCount The count of active tests.
     * @param totalCount The count of total tests.
     */
    public TestCountResponse(long activeCount, long totalCount) {
        this.activeCount = activeCount;
        this.totalCount = totalCount;
    }

    /**
     * Retrieves the count of active tests.
     *
     * @return The count of active tests.
     */
    public long getActiveCount() {
        return activeCount;
    }

    /**
     * Retrieves the count of total tests.
     *
     * @return The count of total tests.
     */
    public long getTotalCount() {
        return totalCount;
    }
}
