import React from 'react'
import { useBackend } from 'main/utils/useBackend';

import BasicLayout from "main/layouts/BasicLayout/BasicLayout";
import StudentsTable from 'main/components/Students/StudentsTable';
import { useCurrentUser } from 'main/utils/currentUser'

export default function StudentsIndexPage() {

  const currentUser = useCurrentUser();

  const { data: students, error: _error, status: _status } =
    useBackend(
      // Stryker disable next-line all : don't test internal caching of React Query
      ["/api/students/all"],
       // Stryker disable next-line StringLiteral : "GET" is the default, so removing it is equivalent
      { method: "GET", url: "/api/students/all" },
      []
    );

  return (
    <BasicLayout>
      <div className="pt-2">
        <h1>Students</h1>
        <StudentsTable students={students} currentUser={currentUser} />
      </div>
    </BasicLayout>
  )
}