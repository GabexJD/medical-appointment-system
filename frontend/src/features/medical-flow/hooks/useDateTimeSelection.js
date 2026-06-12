import { useState, useEffect } from 'react'
import useAvailability from './useAvailability'

function useDateTimeSelection(doctorId) {
  const { groupedByDate, dates, isLoading } = useAvailability(doctorId)
  const [activeDate, setActiveDate] = useState('')

  useEffect(() => {
    if (!activeDate && dates.length > 0) {
      setActiveDate(dates[0])
    }
  }, [dates, activeDate])

  const currentSlots = groupedByDate[activeDate] || []

  return { activeDate, setActiveDate, currentSlots, dates, isLoading }
}

export default useDateTimeSelection
