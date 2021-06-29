# Code notes

```XML
    <DatePicker
        android:id="@+id/datePicker"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:calendarViewShown="false"
        android:datePickerMode="spinner"
        android:foregroundGravity="center"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

```XML
    <TimePicker
        android:id="@+id/timePicker"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:timePickerMode="spinner"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@id/datePicker" />
```
