import { useMemo } from "react";
import OurTable from "main/components/OurTable";

export default function StudentsTable({ students }) {
    const columns = [
        {
            Header: 'id',
            accessor: 'id', // accessor is the "key" in the data
        },
        {
            Header: 'First Name',
            accessor: 'firstName',
        },
        {
            Header: 'Last Name',
            accessor: 'lastName',
        },
        {
            Header: 'Perm',
            accessor: 'perm',
        }
    ];

    // Stryker disable next-line ArrayDeclaration : [students] is a performance optimization
    const memoizedDates = useMemo(() => students, [students]);

    return <OurTable
        data={memoizedDates}
        columns={columns}
        testid={"StudentsTable"}
    />;
};